package com.example.pertemuan10.ui.View.mahasiswa

import androidx.compose.runtime.Composable
import java.lang.reflect.Modifier

@Composable
fun UpdateMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMhsViewModel = viewModel(factory = PenyediaViewModel.Factory) //inisialisasi viewModel
){
    val uiState = viewModel.updateUiState //ambil UI state dari view model
    val snackbarHostState = remember { SnackbarHostState() } //snackbar state
    val coroutineScope = rememberCoroutineScope()

    //observasi perubahan snackbarMessage

    LaunchedEffect(uiState.snackBarMessage) {
        println("LaunchedEffect triggered")
        uiState.snackBarMessage?.let { message ->
            println("Snackbar message received: $message")
            coroutineScope.launch {
                println("Launching coroutine for snackbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}, //Tempatkan snackbar di scaffold
        topBar = {
            TopAppBar(
                judul = " Edit Mahasiswa",
                showBackButton = true,
                onBack = onBack,
            )
        }
    ){
            padding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(padding)
                .padding(16.dp)
        ){
            //Isi body
            InsertBodyMhs(
                uiState = uiState,
                onValueChange = {updateEvent ->
                    viewModel.updateState(updateEvent) // Update state di ViewModel
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main){
                                onNavigate() //Navigasi di main thread
                            }
                        }
                    }
                }
            )
        }
    }
}