<script setup lang="ts">
import { ref } from 'vue'

const searchInput = ref('')
</script>

<template>
  <header class="navbar">
    <div class="navbar-inner container">
      <RouterLink to="/" class="logo">STREAMINHO</RouterLink>

      <input
        v-model="searchInput"
        type="text"
        placeholder="Search movies"
        class="search"
      />
    </div>
  </header>
</template>

<style scoped>
.navbar {
  position: sticky;
  top: 0;
  z-index: 10;
  background: rgba(11, 12, 16, 0.85);
  backdrop-filter: blur(8px);
  border-bottom: 1px solid var(--color-border);
}

.navbar-inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 72px;
}

.logo {
  font-family: var(--font-display);
  font-size: 1.6rem;
  letter-spacing: 2px;
  color: var(--color-accent);
}

.search {
  background: var(--color-surface);
  border: 1px solid var(--color-border);
  border-radius: 6px;
  padding: var(--space-2) var(--space-3);
  color: var(--color-text);
  width: 280px;
  font-family: var(--font-body);
  font-size: 0.9rem;
  outline: none;
  transition: border-color 0.15s ease;
}

.search::placeholder {
  color: var(--color-text-muted);
}

.search:focus {
  border-color: var(--color-accent);
}
</style>