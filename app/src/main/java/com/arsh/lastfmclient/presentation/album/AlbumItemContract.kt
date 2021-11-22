package com.arsh.lastfmclient.presentation.album

/**
 * Interface providing click contracts
 */
interface AlbumItemContract {
    fun favPos(pos: Int)
    fun clickedPos(pos: Int)
    fun localFavoriteState(albumName: String): Boolean
}