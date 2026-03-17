package ca.qc.chc.andriond.listdemo

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ca.qc.chc.andriond.listdemo.ui.theme.NavigationExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnPage(navController: NavController) {

    val countryList = retrieveCountries()
    val myContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lazy Column",
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_500),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(countryList) { country ->

                Card(
                    onClick = {
                        Toast.makeText(
                            myContext,
                            "You selected ${country.countryName}",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(7.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.purple_500)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    // elevation means the card looks like it is raised above the screen.
                    elevation = CardDefaults.cardElevation(7.dp),
                    //BorderStroke creates a border-line around the component.
                    border = BorderStroke(2.dp, Color.Red)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = country.countryImage),
                                contentDescription = country.countryName,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Red, CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier.padding(start = 10.dp)
                            ) {
                                Text(
                                    text = country.countryName,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(3.dp))

                                Text(
                                    text = country.countryDetail,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }

                        Button(
                            onClick = {
                                navController.navigate("DetailsPage/${country.countryId}")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            border = BorderStroke(2.dp, Color.Red)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = "Details",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1080dp,height=2400dp,dpi=440"
)
@Composable
fun ColumnPagePreview() {
    NavigationExampleTheme() {
        val navController = rememberNavController()
        ColumnPage(navController = navController)
    }
}