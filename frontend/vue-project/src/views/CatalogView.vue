<!-- src/views/CatalogoView.vue -->
<script setup lang="ts">
import { ref, onMounted } from 'vue'
import type { Movie } from '@/types/Movie'
import MovieCard from '@/components/MovieCard.vue'
import { getPopularMovies } from '@/services/MovieService'

const movies = ref<Movie[]>([])

onMounted(async () => {
  movies.value = await getPopularMovies()
})
</script>

<template>
  <div class="catalog">
    <h2>Catálogo</h2>
    <div class="grid">
      <MovieCard
        v-for="movie in movies"
        :key="movie.id"
        :movie="movie"
      />
    </div>
  </div>
</template>

<style scoped>
.catalogo {
  background: #0a0a0a;
  min-height: 100vh;
  padding: 24px 40px;
}

.catalogo h2 {
  color: #fff;
  margin-bottom: 16px;
}

.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(160px, 1fr));
  gap: 20px;
}
</style>