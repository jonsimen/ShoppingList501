package com.example.shoppinglist501

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist501.ui.theme.ShoppingList501Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingList501Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextInputWithList()
                }
            }
        }
    }
}

@Composable
fun TextInputWithList() {
    var text by remember { mutableStateOf("") } //ingredient field
    var text2 by remember { mutableStateOf("") } //quantity field
    val items = remember { mutableStateListOf<String>() } //lazylist items list

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextField(
                value = text,
                onValueChange = { text = it }, //input your ingredient
                label = { Text("Ingredient") },
            )

            Spacer(modifier = Modifier.width(10.dp))

            TextField(
                value = text2,
                onValueChange = { text2 = it }, //input your quantity
                label = { Text("#") }
            )
        }
        Row(){
            Button(
                onClick = {
                    if (text.isNotBlank() && text2.isNotBlank()) {
                        items.add("$text2 $text(s)") //inputs are added to list
                        text = ""
                        text2 = "" //let user input new item
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(items) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = { checked ->
                            if (checked) {
                                items.remove(item) //ingredient has been bought, remove from list
                            }
                        }
                    )
                    Text(
                        text = item,
                    )
                }
            }
        }
    }
}
