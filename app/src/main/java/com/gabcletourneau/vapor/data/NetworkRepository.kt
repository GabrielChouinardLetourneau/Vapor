package com.gabcletourneau.vapor.data

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.os.Process

sealed class NetworkTypeResult {
    data class Connected(val transportType: Int, val transportName: String) : NetworkTypeResult()
    object NotConnected : NetworkTypeResult()
    object UnknownOrOther : NetworkTypeResult()
}

fun getCurrentNetworkType(context: Context): Any {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)

    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Log.i("NetworkInfo", "Current network: Wi-Fi")
                return NetworkTypeResult.Connected(NetworkCapabilities.TRANSPORT_WIFI, "Wi-Fi")
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Log.i("NetworkInfo", "Current network: Cellular")
                return NetworkTypeResult.Connected(NetworkCapabilities.TRANSPORT_CELLULAR, "Cellular data")
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Log.i("NetworkInfo", "Current network: Ethernet")
                return NetworkTypeResult.Connected(NetworkCapabilities.TRANSPORT_ETHERNET, "Ethernet connection")
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                Log.i("NetworkInfo", "Current network: VPN")
                return NetworkTypeResult.Connected(NetworkCapabilities.TRANSPORT_VPN, "VPN")
            }
            else -> {
                Log.i("NetworkInfo", "Current network: Other")
                return NetworkTypeResult.UnknownOrOther
            }
        }
    } else {
        Log.i("NetworkInfo", "Not connected to any network.")
        return NetworkTypeResult.NotConnected
    }
}

fun getNetworkUsage(
    context: Context,
    startTime: Long,
    endTime: Long,
    networkType: Int
): NetworkStats.Bucket? {
    val networkStatsManager =
        context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager
    val currentAppUid: Int = Process.myUid()

    var summary: NetworkStats.Bucket? = null

    try {
        // For mobile data
        summary = networkStatsManager.querySummaryForDevice(
            networkType,
            null, // Subscriber ID (can be null for all)
            startTime,
            endTime
        )



    } catch (e: SecurityException) {
        Log.e("NetworkUsage", "Permission denied to query network stats: ${e.message}")
        // This might happen if you try to query for other UIDs without PACKAGE_USAGE_STATS
    }

    return summary
}

fun getNetworkStrength(
    context: Context,
    startTime: Long,
    endTime: Long,
    networkType: Int
): Pair<Long, Long> {

    val networkStatsManager =
        context.getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager
    val currentAppUid: Int = Process.myUid()

    var totalRxBytes = 0L
    var totalTxBytes = 0L

    try {
        // Query details for your app's UID
        val networkStats: NetworkStats = networkStatsManager.queryDetailsForUid(
            networkType,
            null, // subscriberId - use null for Wi-Fi or if you don't have a specific mobile subscriber
            startTime,
            endTime,
            currentAppUid
        )

        val bucket = NetworkStats.Bucket() // Create a Bucket object to reuse
        while (networkStats.hasNextBucket()) {
            networkStats.getNextBucket(bucket)
            // You can also check bucket.uid == currentAppUid if you want to be absolutely sure,
            // though queryDetailsForUid should only return data for the specified UID.
            totalRxBytes += bucket.rxBytes
            totalTxBytes += bucket.txBytes
        }

        // Close the NetworkStats object to release resources
        networkStats.close()

        Log.d(
            "NetworkUsage",
            "App UID ($currentAppUid) - NetworkType ($networkType): " +
                    "Received $totalRxBytes bytes, Sent $totalTxBytes bytes " +
                    "between $startTime and $endTime"
        )

    } catch (e: SecurityException) {
        Log.e("NetworkUsage", "Permission denied to query network stats: ${e.message}")
        // This might happen if you try to query for other UIDs without PACKAGE_USAGE_STATS
        // or if the necessary permissions are missing.
    } catch (e: Exception) {
        Log.e("NetworkUsage", "Error querying network stats: ${e.message}", e)
    }

    return Pair(totalRxBytes, totalTxBytes)
}