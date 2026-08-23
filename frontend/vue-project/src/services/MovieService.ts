import type { Movie } from "@/types/Movie";
import mockData from '@/data/mock.json'

const USE_MOCK = true // cambiar para integrar con api

export async function getPeliculas(): Promise<Movie[]> {
  if (USE_MOCK) {
    return mockData as Movie[]
  }
  const res = await fetch('apiUrl')
  if (!res.ok) throw new Error('Error al cargar peliculas')
  return res.json()
}