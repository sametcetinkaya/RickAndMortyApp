package com.sametcetinkaya.rickandmortyapp.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.sametcetinkaya.rickandmortyapp.MainActivity
import com.sametcetinkaya.rickandmortyapp.R
import com.sametcetinkaya.rickandmortyapp.data.state.ApiState

fun Fragment.shouldShowProgress(isLoading: Boolean) {
    (requireActivity() as MainActivity).shouldShowProgress(isLoading)
}

fun Fragment.showErrorApi(
    shouldCloseTheViewOnApiError: Boolean = false,
    messageBody: String = getString(R.string.error_getting_data)
) {
    val dialog = AlertDialogs(
        kindOfMessage = AlertDialogs.ERROR_MESSAGE,
        messageBody = messageBody,
        clikOnAccept = object : AlertDialogs.ClickOnAccept {
            override fun clickOnAccept() {
                if (shouldCloseTheViewOnApiError) {
                    findNavController().popBackStack()
                }
            }

            override fun clickOnCancel() {

            }

        })
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}

fun Fragment.showErrorNetwork(shouldCloseTheViewOnApiError: Boolean = false) {
    val dialog =
        AlertDialogs(
            kindOfMessage = AlertDialogs.ERROR_MESSAGE,
            messageBody = getString(R.string.error_internet),
            clikOnAccept = object : AlertDialogs.ClickOnAccept {
                override fun clickOnAccept() {
                    if (shouldCloseTheViewOnApiError) {
                        findNavController().popBackStack()
                    }
                }

                override fun clickOnCancel() {

                }
            }
        )
    activity?.let { dialog.show(it.supportFragmentManager, "alertMessage") }
}

fun <T> Fragment.observeApiResultGeneric(
    liveData: LiveData<ApiState<T>>,
    onLoading: () -> Unit = { },
    onFinishLoading: () -> Unit = { },
    hasProgressTheView: Boolean = false,
    shouldCloseTheViewOnApiError: Boolean = false,
    onError: (() -> Unit)? = null,
    noData: () -> Unit = {},
    onSuccess: (data: T) -> Unit,
) {
    liveData.observe(viewLifecycleOwner) { apiState ->
        if (apiState == null) return@observe
        fun handleStatus(isLoading: Boolean) {
            if (isLoading) {
                onLoading()
            } else {
                onFinishLoading()
            }
        }

        val isLoading = apiState is ApiState.Loading
        if (hasProgressTheView) {
            shouldShowProgress(isLoading)
        } else {
            handleStatus(isLoading)
        }
        when (apiState) {
            is ApiState.Success -> {
                if (apiState.data != null) {
                    onSuccess(apiState.data)
                }
            }
            is ApiState.Error -> {
                Log.w(context?.packageName, apiState.message.toString())
                if (onError == null) {
                    showErrorApi(shouldCloseTheViewOnApiError)
                } else {
                    onError()
                }
            }
            is ApiState.ErrorNetwork -> {
                showErrorNetwork(shouldCloseTheViewOnApiError)
            }
            is ApiState.NoData -> {
                noData()
            }
            else -> {}
        }
    }
}