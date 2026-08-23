<script setup lang="ts">
 import type { Movie } from '@/types/Movie';

 defineProps<{
  movie: Movie
}>()
</script>

<template>
  <div class="card">
    <img :src="movie.posterUrl" :alt="movie.title" class="poster" />
    <div class="info">
      <h3>{{ movie.title }}</h3>
      <p class="meta">{{ movie.releaseDate.slice(0, 4) }} · {{ movie.duration }}</p>
    </div>
  </div>
</template>

<style scoped>
.card {
  background: #181818;
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  transition: transform 0.2s ease;
}

.card:hover {
  transform: scale(1.05);
}

.poster {
  width: 100%;
  aspect-ratio: 2 / 3;
  object-fit: cover;
  display: block;
}

.info {
  padding: 10px 12px;
}

.info h3 {
  color: #fff;
  font-size: 0.95rem;
  margin: 0 0 4px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.meta {
  color: #999;
  font-size: 0.8rem;
  margin: 0;
}
</style>