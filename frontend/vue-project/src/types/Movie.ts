
export interface MovieDto {
  id: number
  title: string
  description: string
  releaseDate: string
  duration: string
}

// Usa /api/movie/{id}/poster
export interface Movie extends MovieDto {
  posterUrl: string | undefined
}