package com.example.kavyakanaja.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kavyakanaja.data.Poem

@Composable
fun AllPoemsScreen(
    poems: List<Poem>,
    onClick: (Poem) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("📚 All Poems", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(10.dp))

        LazyColumn {

            items(poems) { poem ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onClick(poem) }
                ) {

                    Column(modifier = Modifier.padding(14.dp)) {

                        Text(poem.title, style = MaterialTheme.typography.titleMedium)
                        Text("— ${poem.poet}", style = MaterialTheme.typography.bodyMedium)

                        Spacer(Modifier.height(6.dp))

                        Text(
                            poem.text.take(60) + "...",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}