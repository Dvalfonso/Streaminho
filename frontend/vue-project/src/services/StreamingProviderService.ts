import type { StreamingProviderDto } from "@/types/StreamingProvider"

const API_BASE = 'http://localhost:8080/api'

export async function getWatchProviders(movieId: number): Promise<StreamingProviderDto[]> {
  const res = await fetch(`${API_BASE}/movies/${movieId}/availability`)
  if (!res.ok) throw new Error('Error loading watch providers')
  return res.json()
}