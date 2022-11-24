package com.aptoide.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aptoide.R
import com.aptoide.data.app.model.AppModel
import com.aptoide.ui.screens.Routers.APP
import com.aptoide.ui.viewModel.ListAppsViewModel
import com.aptoide.ui.viewModel.ResourceState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ListAppsScreen(
    viewModel: ListAppsViewModel = hiltViewModel(),
    navController: NavController
) {


    var currentState = viewModel.list.value

    when (currentState) {
        ResourceState.Empty -> ListAppsEmpty()
        is ResourceState.Success -> AppList(
            listApps = currentState.allApps.sortedByDescending { it.rating },
            navController
        )
        is ResourceState.Loading -> ListAppsLoading()
    }

}

@Composable
fun AppList(listApps: List<AppModel>, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        Row() {
            AppListEditorsChoice(listApps = listApps, navController)
        }

        Row() {
            AppListLocalTop(listApps = listApps, navController)
        }
    }
}

@Composable
fun AppListEditorsChoice(listApps: List<AppModel>, navController: NavController) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .padding(5.dp, 25.dp, 0.dp, 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.editor_choice)
            )
        }
        LazyRow {
            itemsIndexed(listApps) { index, item ->

                Card(
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable {
                            navigateToDetailAppScreen(item, navController)
                        },
                    elevation = 10.dp,
                ) {
                    AppItemGraphic(app = item)
                }

            }

        }
    }

}


@Composable
fun AppListLocalTop(listApps: List<AppModel>, navController: NavController) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .padding(5.dp, 20.dp, 0.dp, 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.local_top)
            )
        }
        LazyRow {
            itemsIndexed(listApps) { index, item ->
                Card(
                    modifier = Modifier
                        .padding(3.dp)
                        .size(110.dp)
                        .align(CenterHorizontally)
                        .clickable {
                            navigateToDetailAppScreen(item, navController)
                        },
                    elevation = 10.dp
                ) { AppItemIcon(app = item) }
            }
        }

    }


}


@Composable
fun AppItemIcon(app: AppModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Row {
            AsyncImage(
                modifier = Modifier
                    .size(75.dp)
                    .padding(0.dp, 5.dp, 1.dp, 0.dp)
                    .clip(RoundedCornerShape(3.dp)),
                model = app.icon,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 2.dp, 0.dp, 0.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(1.dp),
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                text = app.name
            )

        }

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.star_black),
                contentDescription = "",
                modifier = Modifier
                    .size(10.dp)
                    .padding(4.dp, 0.dp, 0.dp, 0.dp)
            )

            Text(
                text = app.rating.toString(),
                modifier = Modifier
                    .size(40.dp)
                    .padding(2.dp, 1.dp, 0.dp, 0.dp),
                color = Color.Black,
                fontSize = 6.sp,
                fontWeight = FontWeight.Bold
            )
        }


    }

}

@Composable
fun AppItemGraphic(app: AppModel) {

    Box {
        AsyncImage(
            model = app.graphic,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    text = app.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.star_white),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(5.dp, 20.dp, 0.dp, 0.dp)
                        .size(30.dp)
                )

                Text(
                    text = app.rating.toString(),
                    modifier = Modifier
                        .padding(2.dp, 26.dp, 0.dp, 0.dp)
                        .size(30.dp),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }

}

@Composable
fun ListAppsEmpty() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 5.dp)
            .background(Color.White)
            .padding(0.dp, 100.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.empty),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ListAppsLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 5.dp)
            .background(Color.White)
    ) {


        Text(
            text = stringResource(R.string.loading),
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

fun navigateToDetailAppScreen(app: AppModel, navController: NavController) {
    navController.currentBackStackEntry?.savedStateHandle?.set(APP, app)
    navController.navigate(Routers.DETAIL_APP_SCREEN)

}


