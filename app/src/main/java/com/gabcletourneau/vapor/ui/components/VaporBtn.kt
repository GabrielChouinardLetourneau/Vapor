package com.gabcletourneau.vapor.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A versatile custom button that can be styled as a filled, outlined, or text button.
 *
 * @param onClick Callback to be invoked when this button is clicked.
 * @param modifier Modifier to be applied to the button.
 * @param type The style of the button (Filled, Outlined, Text).
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be clickable.
 * @param shape Defines the button's shape. Defaults to [ButtonDefaults.shape].
 * @param colors [ButtonColors] that will be used to resolve the colors for this button in different states.
 *               See [ButtonDefaults.buttonColors], [ButtonDefaults.outlinedButtonColors], [ButtonDefaults.textButtonColors].
 * @param elevation [ButtonElevation] used to resolve the elevation for this button in different states.
 *                  This controls the shadow below the button. Pass `null` for no elevation.
 * @param border Border to draw around the button. Relevant for OutlinedButton or if you want a border on a filled button.
 * @param contentPadding The spacing values to apply internally between the button's boundaries and its content.
 * @param interactionSource The [MutableInteractionSource] representing the stream of Interactions for this button.
 *                          You can create and pass in your own `remember`ed instance to observe Interactions and customize
 *                          the appearance / behavior of this button in different states.
 * @param minWidth The minimum width of the button.
 * @param minHeight The minimum height of the button.
 * @param content The content to be displayed inside the button, typically a [Text] composable.
 *                The content is implicitly placed in a [RowScope], so if you provide multiple
 *                composables, they will be laid out horizontally.
 */
@Composable
fun VaporButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: ButtonType = ButtonType.FILLED,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape, // Or MaterialTheme.shapes.medium, etc.
    colors: ButtonColors? = null, // Allow null to use defaults based on type
    elevation: ButtonElevation? = null, // Default elevation based on type
    border: BorderStroke? = null, // Default border based on type
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    minWidth: androidx.compose.ui.unit.Dp = ButtonDefaults.MinWidth,
    minHeight: androidx.compose.ui.unit.Dp = ButtonDefaults.MinHeight,
    content: @Composable RowScope.() -> Unit
) {
    val buttonModifier = modifier.defaultMinSize(minWidth = minWidth, minHeight = minHeight)

    when (type) {
        ButtonType.FILLED -> {
            Button(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                shape = shape,
                colors = colors ?: ButtonDefaults.buttonColors(),
                elevation = elevation ?: ButtonDefaults.buttonElevation(),
                border = border,
                contentPadding = contentPadding,
                interactionSource = interactionSource,
                content = content
            )
        }
        ButtonType.OUTLINED -> {
            OutlinedButton(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                shape = shape,
                colors = colors ?: ButtonDefaults.outlinedButtonColors(),
                elevation = elevation, // Outlined buttons usually have null elevation by default
                border = border ?: ButtonDefaults.outlinedButtonBorder(), // Default outlined border if null
                contentPadding = contentPadding,
                interactionSource = interactionSource,
                content = content
            )
        }
        ButtonType.TEXT -> {
            TextButton(
                onClick = onClick,
                modifier = buttonModifier,
                enabled = enabled,
                shape = shape,
                colors = colors ?: ButtonDefaults.textButtonColors(),
                elevation = elevation, // Text buttons usually have null elevation
                border = border,
                contentPadding = contentPadding,
                interactionSource = interactionSource,
                content = content
            )
        }
    }
}

/**
 * Defines the visual style of the [CustomAppButton].
 */
enum class ButtonType {
    FILLED,
    OUTLINED,
    TEXT
}

// --- Previews ---

@Preview(name = "Filled Button", showBackground = true)
@Composable
fun FilledCustomAppButtonPreview() {
    MaterialTheme {
        VaporButton(onClick = { /*TODO*/ }, type = ButtonType.FILLED) {
            Text("Filled Button")
        }
    }
}

@Preview(name = "Outlined Button", showBackground = true)
@Composable
fun OutlinedCustomAppButtonPreview() {
    MaterialTheme {
        VaporButton(onClick = { /*TODO*/ }, type = ButtonType.OUTLINED) {
            Text("Outlined Button")
        }
    }
}

@Preview(name = "Text Button", showBackground = true)
@Composable
fun TextCustomAppButtonPreview() {
    MaterialTheme {
        VaporButton(onClick = { /*TODO*/ }, type = ButtonType.TEXT) {
            Text("Text Button")
        }
    }
}

@Preview(name = "Disabled Filled Button", showBackground = true)
@Composable
fun DisabledFilledCustomAppButtonPreview() {
    MaterialTheme {
        VaporButton(onClick = { /*TODO*/ }, type = ButtonType.FILLED, enabled = false) {
            Text("Disabled Filled")
        }
    }
}

@Preview(name = "Custom Colors Filled", showBackground = true)
@Composable
fun CustomColorsFilledButtonPreview() {
    MaterialTheme {
        VaporButton(
            onClick = { /*TODO*/ },
            type = ButtonType.FILLED,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text("Custom Colors")
        }
    }
}

@Preview(name = "Custom Shape Outlined", showBackground = true)
@Composable
fun CustomShapeOutlinedButtonPreview() {
    MaterialTheme {
        VaporButton(
            onClick = { /*TODO*/ },
            type = ButtonType.OUTLINED,
            shape = MaterialTheme.shapes.large // Example: using a different shape
        ) {
            Text("Custom Shape")
        }
    }
}