package com.funrisestudio.buzzmessenger.ui.main

import com.funrisestudio.buzzmessenger.core.mvi.Action
import com.funrisestudio.buzzmessenger.core.mvi.ViewState
import com.funrisestudio.buzzmessenger.domain.dialogs.Dialog

sealed class DialogsAction : Action {
    object LoadDialogs : DialogsAction()
    class DialogsLoaded(val dialogs: List<Dialog>) : DialogsAction()
    class DialogsError(val throwable: Throwable) : DialogsAction()
    object Loading : DialogsAction()
}

data class DialogsViewState(
    val items: List<DialogViewData>,
    val isLoading: Boolean,
    val error: String
) : ViewState {

    companion object {

        fun createEmpty(): DialogsViewState {
            return DialogsViewState(
                items = emptyList(),
                isLoading = false,
                error = ""
            )
        }

        fun createLoadingState(): DialogsViewState {
            return DialogsViewState(
                items = emptyList(),
                isLoading = true,
                error = ""
            )
        }

        fun createDialogsReceived(dialogs: List<DialogViewData>): DialogsViewState {
            return DialogsViewState(
                items = dialogs,
                isLoading = false,
                error = ""
            )
        }

        fun createErrorState(
            currentDialogs: List<DialogViewData>,
            errorMsg: String
        ): DialogsViewState {
            return DialogsViewState(
                items = currentDialogs,
                isLoading = false,
                error = errorMsg
            )
        }

    }

}