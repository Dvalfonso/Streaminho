<script setup lang="ts">
import type { StreamingProviderDto } from '@/types/StreamingProvider';


defineProps<{
  providers: StreamingProviderDto[]
}>()

function onLogoError(e: Event) {
  const img = e.target as HTMLImageElement
  img.src = 'https://placehold.co/64x64/15171c/8a8f98?text=?'
}
</script>

<template>
  <div v-if="providers.length" class="where-to-watch">
    <h3 class="title">Where to watch</h3>
    <div class="list">
      <div v-for="provider in providers" :key="provider.name" class="provider">
        <img
          :src="provider.logoUrl"
          :alt="provider.name"
          class="logo"
          @error="onLogoError"
        />
        <span class="name">{{ provider.name }}</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.where-to-watch {
  margin-top: var(--space-6);
}

.title {
  font-family: var(--font-display);
  font-size: 1.4rem;
  letter-spacing: 1px;
  margin-bottom: var(--space-3);
}

.list {
  display: flex;
  gap: var(--space-4);
  flex-wrap: wrap;
}

.provider {
  display: flex;
  align-items: center;
  gap: var(--space-2);
  background: var(--color-surface);
  border: 1px solid var(--color-border);
  border-radius: 6px;
  padding: var(--space-2) var(--space-3);
}

.logo {
  width: 32px;
  height: 32px;
  border-radius: 6px;
  object-fit: cover;
}

.name {
  font-size: 0.85rem;
  color: var(--color-text);
}
</style>