package com.example.westpactechnicalassessment.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.westpactechnicalassessment.usecase.IGetCardsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardScreenVM @Inject constructor(
    private val getCardsUC: IGetCardsUC,
): ViewModel() {

    private val _stateFlow = MutableStateFlow<CardScreenState>(CardScreenState.Initial)
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _stateFlow.emit(CardScreenState.Loading)
            getCardsUC()?.let {
                _stateFlow.emit(CardScreenState.ShowCards(it))
            } ?: _stateFlow.emit(CardScreenState.FailedToLoad)
        }
    }
}