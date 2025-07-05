package com.example.ui.widget.other

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.utils.FormatDate

@RequiresApi(Build.VERSION_CODES.O)
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
        fontSize = 14.sp,
        color = color
    )
}