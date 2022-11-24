package com.aptoide.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aptoide.R
import com.aptoide.data.app.model.AppModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailAppScreen(app: AppModel) {

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                modifier = Modifier
                    .padding(4.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                text = app.name
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(3.dp)

                    ),
                model = app.graphic,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(2.dp, 4.dp, 0.dp, 0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.store_name)
            )

            Text(
                modifier = Modifier
                    .padding(2.dp, 4.dp, 0.dp, 0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = app.storeName
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(2.dp, 4.dp, 0.dp, 0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.added)
            )

            Text(
                modifier = Modifier
                    .padding(2.dp, 4.dp, 0.dp, 0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = app.added
            )
        }


    }
}

