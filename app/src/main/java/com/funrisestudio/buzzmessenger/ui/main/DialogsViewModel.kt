package com.funrisestudio.buzzmessenger.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.funrisestudio.buzzmessenger.core.mvi.Store
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DialogsViewModel @ViewModelInject constructor(
    store: Store<DialogsAction, DialogsViewState>
): ViewModel() {

    private val _viewState = MutableLiveData<DialogsViewState>()
    val viewState: LiveData<DialogsViewState> = _viewState

    init {
        store.wire(viewModelScope)
        store.observeViewState()
            .distinctUntilChanged()
            .onEach {
                _viewState.value = it
            }
            .launchIn(viewModelScope)
        store.processAction(DialogsAction.LoadDialogs)
    }

}