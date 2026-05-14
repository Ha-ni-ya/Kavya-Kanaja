package com.example.kavyakanaja.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kavyakanaja.data.PoemRepository
import com.example.kavyakanaja.utils.FavouriteManager

@Composable
fun FavouriteScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val poems = remember {
        PoemRepository.loadPoems(context)
    }

    val favouriteIds =
        FavouriteManager.getAllFavourites(context)

    val favouritePoems = poems.filter {

        favouriteIds.contains(it.id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "❤️ Favourite Poems",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (favouritePoems.isEmpty()) {

            Text(
                text = "No favourite poems yet"
            )

        } else {

            LazyColumn {

                items(favouritePoems) { poem ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {

                                navController.navigate(
                                    "detail/${poem.id}"
                                )
                            }
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = poem.title,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = poem.poet,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                navController.popBackStack()
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("⬅ Back")
        }
    }
}