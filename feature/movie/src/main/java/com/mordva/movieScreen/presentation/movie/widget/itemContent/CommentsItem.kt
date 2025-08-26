package com.mordva.movieScreen.presentation.movie.widget.itemContent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.R
import com.mordva.model.movie.Comment
import com.mordva.movieScreen.presentation.movie.widget.listItem.CommentCard
import com.mordva.ui.widget.component.TitleRow
import com.mordva.ui.widget.lazyComponent.DefaultLazyRow

internal fun LazyListScope.commentsItem(comments: List<Comment>) {
    if (comments.isEmpty()) return

    item {
        TitleRow(title = stringResource(R.string.review)) {}

        DefaultLazyRow(
            list = comments,
            lastItemCard = {}
        ) { comment ->
            CommentCard(comment)
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}