package com.tjclawson.spacex.ui

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tjclawson.spacex.model.Launch
import com.tjclawson.spacex.service.SpaceXApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class SharedViewModel(state: SavedStateHandle) : ViewModel() {

    companion object {
        const val STATE_KEY = "state_key"
    }

    val selectedLaunch = MutableLiveData<Launch>()
    val launchList = MutableLiveData<MutableList<Launch>>()
    var twoPane: Boolean = false
    private val spaceXApiService = SpaceXApiService()
    private val savedStateHandle = state

    // Populates launch list when instantiated
    init {
        getPastLaunchesDescOrder()
    }

    // sets launch that detail fragment observes
    fun selectLaunch(position: Int) {
        selectedLaunch.value = launchList.value?.get(position)
    }

    // stores recyclerview state in the saved state handle so it can be retrieved when screen is
    // rotated
    fun saveAdapterState(adapterState: Parcelable?) {
        savedStateHandle.set(STATE_KEY, adapterState)
    }

    fun getAdapterState(): Parcelable? {
        return savedStateHandle.get(STATE_KEY)
    }

    private fun getPastLaunchesDescOrder() {
        // Runs retrofit call on background thread, try-catch block for basic exception handling
        GlobalScope.launch {
            try {
                launchList.postValue(spaceXApiService?.getPastLaunches("desc"))
            } catch (e: Exception) {
                Log.d("SharedViewModel", e.toString())
            }
        }
    }
}