package com.otero.tvmazeapp.domain.usecase

import com.otero.tvmazeapp.data.Resource
import com.otero.tvmazeapp.data.Status
import com.otero.tvmazeapp.data.repository.EpisodeRepository
import com.otero.tvmazeapp.domain.model.EpisodeItemResult
import com.otero.tvmazeapp.domain.model.SeasonHeader
import com.otero.tvmazeapp.domain.model.EpisodeBySeasonModel as EpisodeBySeasonModel1

class GetEpisodeByShow(
        private val episodeRepository: EpisodeRepository
) : GetEpisodeByShowUseCase {
    override suspend fun invoke(id: Int): Resource<EpisodeBySeasonModel1> {
        val tvEpisodesByShowId = episodeRepository.getTvEpisodesByShowId(id)
        val episodeBySeasonUiModel = EpisodeBySeasonModel1()

        return if (tvEpisodesByShowId.status == Status.SUCCESS) {
            tvEpisodesByShowId.data?.let {
                val data = tvEpisodesByShowId.data.groupBy { it.season }
                for (k in data.keys) {
                    val elements = data[k]?.map {
                        EpisodeItemResult(it.id, it.name)
                    } ?: emptyList()

                    episodeBySeasonUiModel.list.add(SeasonHeader(k))
                    episodeBySeasonUiModel.list.addAll(elements)
                }
            }

            Resource(
                    status = tvEpisodesByShowId.status,
                    data = episodeBySeasonUiModel,
                    message = tvEpisodesByShowId.message)
        } else {
            Resource(
                    status = tvEpisodesByShowId.status,
                    data = episodeBySeasonUiModel,
                    message = tvEpisodesByShowId.message)
        }
    }
}