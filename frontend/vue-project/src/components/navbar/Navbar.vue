<script setup lang="ts">
import { ref } from 'vue'

const searchInput = ref('')
</script>

<template>
  <header class="navbar">
    <RouterLink to="/" class="logo">STREAMINHO</RouterLink>

    <input
      v-model="searchInput"
      type="text"
      placeholder="Search movies"
      class="search"
    />
  </header>
</template>

<style scoped>
.navbar {
  position: sticky;
  top: 0;
  z-index: 10;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 40px;
  background: #0a0a0a;
  border-bottom: 1px solid #222;
}

.logo {
  color: #e50914;
  font-weight: 800;
  font-size: 1.3rem;
  letter-spacing: 1px;
  text-decoration: none;
}

.search {
  background: #181818;
  border: 1px solid #333;
  border-radius: 4px;
  padding: 8px 12px;
  color: #fff;
  width: 260px;
  outline: none;
}

.search:focus {
  border-color: #e50914;
}
</style>