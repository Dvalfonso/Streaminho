<!-- src/views/CatalogoView.vue -->
<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import type { Movie } from '@/types/Movie'
import MovieCard from '@/components/MovieCard.vue'
import { getPopularMovies } from '@/services/MovieService'
import { useSearchStore } from '@/stores/search'

const movies = ref<Movie[]>([])
const searchStore = useSearchStore()

onMounted(async () => {
  movies.value = await getPopularMovies()
})

const filteredMovies = computed(() => {
  const q = searchStore.query.trim().toLowerCase()
  if (!q) return movies.value
  return movies.value.filter(movie =>
    movie.title.toLowerCase().includes(q)
  )
})
</script>

<template>
  <section class="catalog container">
    <div class="section-header">
      <span class="eyebrow">Right now</span>
      <h2>Popular</h2>
    </div>

    <div class="grid">
      <MovieCard
        v-for="movie in filteredMovies"
        :key="movie.id"
        :movie="movie"
      />
    </div>
  </section>
</template>

<style scoped>
.catalog {
  padding-top: var(--space-6);
  padding-bottom: var(--space-8);
}

.section-header {
  margin-bottom: var(--space-5);
}

.eyebrow {
  display: block;
  font-size: 0.75rem;
  font-weight: 600;
  letter-spacing: 1.5px;
  text-transform: uppercase;
  color: var(--color-accent);
  margin-bottom: var(--space-1);
}

.section-header h2 {
  font-family: var(--font-display);
  font-size: 2.2rem;
  letter-spacing: 1px;
  color: var(--color-text);
}

.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(170px, 1fr));
  gap: var(--space-5);
}
</style>