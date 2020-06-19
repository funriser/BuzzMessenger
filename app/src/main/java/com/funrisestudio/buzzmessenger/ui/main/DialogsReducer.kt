package com.funrisestudio.buzzmessenger.ui.main

import com.funrisestudio.buzzmessenger.core.mvi.Reducer
import com.funrisestudio.buzzmessenger.ui.utils.ErrorHandler
import javax.inject.Inject

class DialogsReducer @Inject constructor(
    private val dialogViewDataMapper: DialogViewDataMapper,
    private val errorHandler: ErrorHandler
): Reducer<DialogsAction, DialogsViewState> {

    override fun reduce(viewState: DialogsViewState, action: DialogsAction): DialogsViewState {
        return when(action) {
            is DialogsAction.DialogsLoaded -> {
                val viewData = dialogViewDataMapper.getDialogViewDataList(action.dialogs)
                DialogsViewState.createDialogsReceived(viewData)
            }
            is DialogsAction.Loading -> {
                DialogsViewState.createLoadingState()
            }
            is DialogsAction.DialogsError -> {
                val errMsg = errorHandler.getErrorMessage(action.throwable)
                DialogsViewState.createErrorState(viewState.items, errMsg)
            }
            else -> {
                viewState
            }
        }
    }

}