package com.example.movieapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.app.navigation.HomeRoute
import com.example.movieapp.viewModels.StartViewModel

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: StartViewModel
) {
    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        viewModel.setEntryState(true)
                        navController.navigate(HomeRoute)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text(stringResource(R.string.entry))
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        viewModel.setEntryState(true)
                        navController.navigate(HomeRoute)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text(stringResource(R.string.registration))
                }
            }
        }
    }
}