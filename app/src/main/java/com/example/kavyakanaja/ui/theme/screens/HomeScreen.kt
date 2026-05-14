package com.example.kavyakanaja.ui.theme.screens

import java.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kavyakanaja.data.PoemRepository

@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current

    val poems = remember {
        PoemRepository.loadPoems(context)
    }

    val day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)

    val poemOfDay = poems[day % poems.size]

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFF8E7),
                        Color(0xFFFBE9E7)
                    )
                )
            )
            .padding(16.dp)
    ) {

        // APP TITLE
        item {

            Text(
                text = "ಕಾವ್ಯ ಕಣಜ",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Reviving Kannada Literary Pride",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        // POEM OF DAY CARD
        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "🌸 Poem of the Day",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = poemOfDay.title,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = poemOfDay.text,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "- ${poemOfDay.poet}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            navController.navigate(
                                "detail/${poemOfDay.id}"
                            )
                        },
                        shape = RoundedCornerShape(14.dp)
                    ) {

                        Icon(
                            Icons.Default.MenuBook,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Read More")
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // BUTTONS ROW
        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                ElevatedButton(
                    onClick = {
                        navController.navigate("favourites")
                    },
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text("Favourites")
                }

                ElevatedButton(
                    onClick = {
                        navController.navigate("poets")
                    },
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Icon(
                        Icons.Default.Person,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text("Poet's Corner")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // ALL POEMS TITLE
        item {

            Text(
                text = "📖 Explore Poems",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        // POEMS LIST
        items(poems) { poem ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate(
                            "detail/${poem.id}"
                        )
                    },
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = poem.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = poem.poet,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // BOTTOM SPACE
        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}