package exh.md.follows

import eu.kanade.tachiyomi.source.model.FilterList
import eu.kanade.tachiyomi.source.online.all.MangaDex
import eu.kanade.tachiyomi.ui.browse.source.browse.BrowseSourceScreenModel
import exh.metadata.metadata.base.RaisedSearchMetadata
import exh.source.getMainSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import tachiyomi.data.source.SourcePagingSourceType
import tachiyomi.domain.manga.model.Manga

class MangaDexFollowsScreenModel(sourceId: Long) : BrowseSourceScreenModel(sourceId, null) {

    override fun createSourcePagingSource(query: String, filters: FilterList): SourcePagingSourceType {
        return MangaDexFollowsPagingSource(source.getMainSource() as MangaDex)
    }

    override fun Flow<Manga>.combineMetadata(dbManga: Manga, metadata: RaisedSearchMetadata?): Flow<Pair<Manga, RaisedSearchMetadata?>> {
        return map { it to metadata }
    }

    init {
        mutableState.update { it.copy(filterable = false) }
    }
}
