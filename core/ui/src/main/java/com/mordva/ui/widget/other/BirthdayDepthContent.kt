package com.mordva.ui.widget.other

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mordva.ui.theme.Typography
import com.mordva.util.convert.FormatDate

@Composable
fun BirthdayDepthContent(
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    birthday: String?,
    death: String?
) {
    var text = ""
    
    if (birthday != null && death != null) {
        val prettyBirthday = FormatDate.formatDate(birthday)
        val prettyDepth = FormatDate.formatDate(death)
        text = "$prettyBirthday - $prettyDepth"
    }
    else if (birthday != null) {
        val prettyBirthday = FormatDate.formatDate(birthday)
        text = prettyBirthday
    }
    else if (death != null) {
        val prettyDepth = FormatDate.formatDate(death)
        text = prettyDepth
    }

    Text(
        text = text,
        fontSize = Typography.bodyMedium.fontSize,
        color = color
    )
}