package com.example.cuadriculafinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cuadricula.data.DataSource
import com.example.cuadricula.model.Topic
import com.example.cuadriculafinal.ui.theme.CuadriculaFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuadriculaFinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid(DataSource.topics)
                }
            }
        }
    }
}


@Composable
fun AppCuadricula() {
    //CuadriculaList(listaCuadricula=DataSource.topics)
    TopicGrid(DataSource.topics)
}

@Preview(showBackground = true)
@Composable
fun CuadriculaCardPreview() {
    //CuadriculaCard(Topic(R.string.architecture, 58, R.drawable.architecture))
    //CuadriculaList(listaCuadricula=DataSource.topics)
    TopicGrid(DataSource.topics)
}

@Composable
fun TopicGrid(topics: List<Topic>,modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier,
    ) {
        items(topics) { topic ->
            CuadriculaCard(topic)
        }
    }
}


@Composable
fun CuadriculaCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier=modifier
        .height(68.dp)) {
        Row {
            Image(painter = painterResource(id = topic.imageRes),
                contentDescription = "",
                modifier = Modifier
                    .height(68.dp) ,
                contentScale= ContentScale.Crop
            )
            Column(modifier=Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_medium)
                )
            ) {
                Text(text = LocalContext.current.getString(topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding( bottom = dimensionResource(R.dimen.padding_small)))
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                    )

                    Text(text = topic.availableCourses.toString(),
                        style=MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
                    )

                }
            }
        }


    }
}

@Composable
fun CuadriculaList(listaCuadricula: List<Topic>, modifier: Modifier=Modifier) {
    LazyColumn(modifier = modifier){
        items(listaCuadricula){topic ->
            CuadriculaCard(
                topic = topic,
                modifier =Modifier.padding(8.dp)

            )
        }
    }
}