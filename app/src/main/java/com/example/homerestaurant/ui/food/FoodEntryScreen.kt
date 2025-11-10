package com.example.homerestaurant.ui.food

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homerestaurant.HomeRestaurantTopAppBar
import com.example.homerestaurant.R
import com.example.homerestaurant.data.FoodCategory
import com.example.homerestaurant.ui.AppViewModelProvider
import com.example.homerestaurant.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object FoodEntryDestination : NavigationDestination {
    override val route = "food_entry"
    override val titleRes = R.string.food_entry_title
}

// main food entry screen composable with top app bar and food entry body
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: FoodEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            HomeRestaurantTopAppBar(
                title = stringResource(FoodEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        FoodEntryBody(
            foodUiState = viewModel.foodUiState,
            onFoodValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveFood()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

// composable containing food entry form and save button
@Composable
fun FoodEntryBody(
    foodUiState: FoodUiState,
    onFoodValueChange: (FoodDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FoodEntryForm(
            foodDetails = foodUiState.foodDetails,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onFoodValueChange
        )
        Button(
            onClick = onSaveClick,
            enabled = foodUiState.isEntryValid,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_button))
        }
    }
}

// outlined text fields for name, rating, and prep time along with category selector composable
@Composable
fun FoodEntryForm(
    foodDetails: FoodDetails,
    modifier: Modifier = Modifier,
    onValueChange: (FoodDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = foodDetails.name,
            onValueChange = { onValueChange(foodDetails.copy(name = it)) },
            label = { Text(stringResource(R.string.food_name_req)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = foodDetails.rating,
            onValueChange = { onValueChange(foodDetails.copy(rating = it)) },
            label = { Text(stringResource(R.string.food_rating_req)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = foodDetails.prepTime,
            onValueChange = { onValueChange(foodDetails.copy(prepTime = it)) },
            label = { Text(stringResource(R.string.prep_time_req)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        FoodCategorySelector(
            selectedCategory = foodDetails.foodCategory,
            onCategorySelected = { onValueChange(foodDetails.copy(foodCategory = it)) }
        )
    }
}

// radio buttons to select food category
@Composable
fun FoodCategorySelector(
    selectedCategory: FoodCategory,
    onCategorySelected: (FoodCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        FoodCategory.values().forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategorySelected(category) }
            ) {
                RadioButton(
                    selected = (category == selectedCategory),
                    onClick = { onCategorySelected(category) }
                )
                Text(
                    text = category.name.lowercase().replaceFirstChar { it.uppercase() }
                )
            }
        }
    }
}

// TODO: Working on image uploading capability

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageUploadCard(
    imageUri: String?,
    onImageSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var currentUri by remember { mutableStateOf<Uri?>(imageUri?.let { Uri.parse(it) }) }

    // Variables for ModalBottomSheet
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    // Gallery launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            currentUri = it
            onImageSelected(it.toString())
        }
        showSheet = false
    }

    // Camera launcher


}

/* TEMPLATE
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageUploadCard(
    imageUri: String?,
    onImageSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var currentUri by remember { mutableStateOf<Uri?>(imageUri?.let { Uri.parse(it) }) }

    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    // Gallery launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            currentUri = it
            onImageSelected(it.toString())
        }
        showSheet = false
    }

    // Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) currentUri?.let { onImageSelected(it.toString()) }
        showSheet = false
    }

    // Launch camera function
    fun launchCamera() {
        val imageFile = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "${UUID.randomUUID()}.jpg"
        )
        currentUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            imageFile
        )
        cameraLauncher.launch(currentUri)
    }

    // Main card UI
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Food Image",
                style = MaterialTheme.typography.titleMedium
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable { showSheet = true }, // Open bottom sheet
                contentAlignment = Alignment.Center
            ) {
                if (currentUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = currentUri),
                        contentDescription = "Selected Food Image",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text(
                        text = "Tap here to select an image",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    // Bottom sheet for choosing Gallery or Camera
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Choose Image Source",
                    style = MaterialTheme.typography.titleMedium
                )
                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Select from Gallery")
                }
                Button(
                    onClick = { launchCamera() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Take a Photo")
                }
            }
        }
    }
}

 */

 */

// Previews

@Preview
@Composable
fun FoodCategorySelectorPreview() {
    FoodCategorySelector(
        selectedCategory = FoodCategory.ENTREE,
        onCategorySelected = { }
    )
}

@Preview
@Composable
fun FoodEntryFormPreview() {
    FoodEntryForm(
        foodDetails = FoodDetails(),
    )
}

@Preview
@Composable
fun FoodEntryBodyPreview() {
    FoodEntryBody(
        foodUiState = FoodUiState(foodDetails = FoodDetails(), isEntryValid = true),
        onFoodValueChange = { FoodDetails() },
        onSaveClick = { }
    )
}

@Preview
@Composable
fun FoodEntryScreenPreview() {
    FoodEntryScreen(
        navigateBack = {},
        onNavigateUp = {}
    )
}