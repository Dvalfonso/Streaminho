<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import type { Movie } from '@/types/Movie'
import { getMovieById } from '@/services/MovieService'
import type { TrailerDto } from '@/types/Trailer'
import { getMovieTrailers } from '@/services/TrailerService'
import TrailerList from '@/components/trailerList/TrailerList.vue'

const route = useRoute()
const movie = ref<Movie | null>(null)
const trailers = ref<TrailerDto[]>([])
const loading = ref(true)
const error = ref<string | null>(null)

onMounted(async () => {
  try {
    const id = Number(route.params.id)
    const [movieData, trailersData] = await Promise.all([
      getMovieById(id),
      getMovieTrailers(id)
    ])
    movie.value = movieData
    trailers.value = trailersData
  } catch (e) {
    error.value = e instanceof Error ? e.message : 'Unknown error'
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <section class="detail container">
    <p v-if="loading" class="state">Loading...</p>
    <p v-else-if="error" class="state">{{ error }}</p>

    <div v-else-if="movie">
      <div class="content">
        <img :src="movie.posterUrl" :alt="movie.title" class="poster" />
        <div class="info">
          <h1>{{ movie.title }}</h1>
          <p class="meta">{{ movie.releaseDate.slice(0, 4) }} · {{ movie.duration }}</p>
          <p class="description">{{ movie.description }}</p>
        </div>
      </div>

      <TrailerList :trailers="trailers" />
    </div>
  </section>
</template>

<style scoped>
.detail {
  padding-top: var(--space-6);
  padding-bottom: var(--space-8);
}

.state {
  color: var(--color-text-muted);
}

.content {
  display: grid;
  grid-template-columns: 300px 1fr;
  gap: var(--space-6);
}

.poster {
  width: 100%;
  border-radius: 8px;
  border: 1px solid var(--color-border);
}

.info h1 {
  font-family: var(--font-display);
  font-size: 2.8rem;
  letter-spacing: 1px;
  margin-bottom: var(--space-2);
}

.meta {
  color: var(--color-accent);
  font-size: 0.9rem;
  margin-bottom: var(--space-4);
}

.description {
  color: var(--color-text-muted);
  line-height: 1.6;
  max-width: 60ch;
}
</style>