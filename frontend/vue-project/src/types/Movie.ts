
export interface MovieDto {
  id: number
  title: string
  description: string
  releaseDate: string
  duration: string
  posterPath: string | null
  popularity: number
}

export interface Movie extends MovieDto {
  posterUrl: string
}