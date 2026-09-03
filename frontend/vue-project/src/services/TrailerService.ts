import type { TrailerDto } from '@/types/Trailer'

const API_BASE = 'http://localhost:8080'

export async function getMovieTrailers(movieId: number): Promise<TrailerDto[]> {
  const res = await fetch(`${API_BASE}/api/movies/${movieId}/trailers`)
  if (!res.ok) throw new Error('Error loading trailers')
  const trailers: TrailerDto[] = await res.json()

  return trailers.filter(t => t.type === 'Trailer')
}