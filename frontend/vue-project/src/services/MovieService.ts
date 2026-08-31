import type { Movie, MovieDto } from "@/types/Movie"

const API_BASE = 'http://localhost:8080'
const TMDB_IMG_BASE = 'https://image.tmdb.org/t/p/w500'
const POSTER_FALLBACK = 'https://placehold.co/300x450/181818/666?text=Sin+poster'

function joinPosterUrl(posterPath: string | null): string {
  return posterPath ? `${TMDB_IMG_BASE}${posterPath}` : POSTER_FALLBACK
}


export async function getPopularMovies(): Promise<Movie[]> {
  
  const res = await fetch(`${API_BASE}/api/movies/popular`)
  if (!res.ok) throw new Error('Error loading popular movies')
  
  const movies: MovieDto[] = await res.json()

  return movies.map(movie => ({
    ...movie,
    posterUrl: joinPosterUrl(movie.posterPath)
  }))
}

export async function getMovieById(id: number): Promise<Movie> {
  const res = await fetch(`${API_BASE}/api/movies/${id}`)
  if (!res.ok) throw new Error('Error loading movie')

  const movie: MovieDto = await res.json()

  return {
    ...movie,
    posterUrl: joinPosterUrl(movie.posterPath)
  }
}