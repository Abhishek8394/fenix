/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.library.historymetadata

import mozilla.components.lib.state.Action
import mozilla.components.lib.state.State
import mozilla.components.lib.state.Store
import org.mozilla.fenix.library.history.History

/**
 * The [Store] for holding the [HistoryMetadataGroupFragmentState] and applying
 * [HistoryMetadataGroupFragmentAction]s.
 */
class HistoryMetadataGroupFragmentStore(initialState: HistoryMetadataGroupFragmentState) :
    Store<HistoryMetadataGroupFragmentState, HistoryMetadataGroupFragmentAction>(
        initialState,
        ::historyStateReducer
    )

/**
 * Actions to dispatch through the [HistoryMetadataGroupFragmentStore to modify the
 * [HistoryMetadataGroupFragmentState] through the [historyStateReducer].
 */
sealed class HistoryMetadataGroupFragmentAction : Action {
    data class UpdateHistoryItems(val items: List<History.Metadata>) :
        HistoryMetadataGroupFragmentAction()
    data class Select(val item: History.Metadata) : HistoryMetadataGroupFragmentAction()
    data class Deselect(val item: History.Metadata) : HistoryMetadataGroupFragmentAction()
    object DeselectAll : HistoryMetadataGroupFragmentAction()
}

/**
 * The state for [HistoryMetadataGroupFragment].
 *
 * @property items The list of [History.Metadata] to display.
 */
data class HistoryMetadataGroupFragmentState(
    val items: List<History.Metadata> = emptyList()
) : State

/**
 * Reduces the history metadata state from the current state with the provided [action] to be
 * performed.
 *
 * @param state The current history metadata state.
 * @param action The action to be performed on the state.
 * @return the new [HistoryMetadataGroupFragmentState] with the [action] executed.
 */
private fun historyStateReducer(
    state: HistoryMetadataGroupFragmentState,
    action: HistoryMetadataGroupFragmentAction
): HistoryMetadataGroupFragmentState {
    return when (action) {
        is HistoryMetadataGroupFragmentAction.UpdateHistoryItems ->
            state.copy(items = action.items)
        is HistoryMetadataGroupFragmentAction.Select ->
            state.copy(
                items = state.items.toMutableList()
                    .map {
                        if (it == action.item) {
                            it.copy(selected = true)
                        } else {
                            it
                        }
                    }
            )
        is HistoryMetadataGroupFragmentAction.Deselect ->
            state.copy(
                items = state.items.toMutableList()
                    .map {
                        if (it == action.item) {
                            it.copy(selected = false)
                        } else {
                            it
                        }
                    }
            )
        is HistoryMetadataGroupFragmentAction.DeselectAll ->
            state.copy(
                items = state.items.toMutableList()
                    .map { it.copy(selected = false) }
            )
    }
}
