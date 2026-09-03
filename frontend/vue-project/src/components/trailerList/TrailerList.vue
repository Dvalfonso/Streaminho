<script setup lang="ts">
import { ref } from 'vue'
import type { TrailerDto } from '@/types/Trailer'

defineProps<{
  trailers: TrailerDto[]
}>()

const playingKey = ref<string | null>(null)

function play(key: string) {
  playingKey.value = key
}
</script>

<template>
  <div v-if="trailers.length" class="trailers">
    <h3 class="title">Trailers &amp; clips</h3>

    <div class="grid">
      <div v-for="trailer in trailers" :key="trailer.id" class="item">
        <div v-if="playingKey === trailer.youtubeKey" class="player-wrap">
          <iframe
            :src="`https://www.youtube.com/embed/${trailer.youtubeKey}?autoplay=1`"
            title="YouTube trailer"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen
          />
        </div>

        <button v-else class="thumb-wrap" @click="play(trailer.youtubeKey)">
          <img
            :src="`https://img.youtube.com/vi/${trailer.youtubeKey}/hqdefault.jpg`"
            :alt="trailer.type"
            class="thumb"
          />
          <span class="play-icon">▶</span>
        </button>

        <p class="name">{{ trailer.type }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
    .trailers {
    margin-top: var(--space-7);
    }

    .title {
    font-family: var(--font-display);
    font-size: 1.6rem;
    letter-spacing: 1px;
    margin-bottom: var(--space-4);
    }

    .grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
    gap: var(--space-5);
    }

    .thumb-wrap,
    .player-wrap {
    position: relative;
    aspect-ratio: 16 / 9;
    border-radius: 6px;
    overflow: hidden;
    border: 1px solid var(--color-border);
    }

    .thumb-wrap {
    display: block;
    width: 100%;
    padding: 0;
    background: var(--color-surface);
    cursor: pointer;
    }

    .thumb {
    width: 100%;
    height: 100%;
    object-fit: cover;
    display: block;
    }

    .play-icon {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 48px;
    height: 48px;
    display: flex;
    align-items: center;
    justify-content: center;
    background: rgba(11, 12, 16, 0.75);
    border: 2px solid var(--color-accent);
    border-radius: 50%;
    color: var(--color-accent);
    font-size: 1rem;
    }

    .player-wrap iframe {
    width: 100%;
    height: 100%;
    border: 0;
    }

    .name {
    margin-top: var(--space-2);
    font-size: 0.85rem;
    color: var(--color-text-muted);
    }
</style>