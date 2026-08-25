import CatalogView from '@/views/CatalogView.vue'
import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: CatalogView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/CatalogView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
