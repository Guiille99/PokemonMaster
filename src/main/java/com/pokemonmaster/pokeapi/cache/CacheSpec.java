package com.pokemonmaster.pokeapi.cache;

import java.util.function.Supplier;

import lombok.Getter;
import reactor.core.publisher.Mono;

@Getter
public class CacheSpec<T> {
    private String key;
	private Class<T> type;
	private Supplier<Mono<T>> monoSupplier;

    public static <T> CacheSpecBuilder<T> get(Class<T> type, String key) {
		CacheSpecBuilder<T> builder = new CacheSpecBuilder<>();
		builder.spec = new CacheSpec<>();
		builder.spec.key = key;
		builder.spec.type = type;

		return builder;
	}

	public static class CacheSpecBuilder<T> {
		private CacheSpec<T> spec;
		
		private CacheSpecBuilder() {
			
		}

		public CacheSpec<T> orCache(Supplier<Mono<T>> monoSupplier) {
			this.spec.monoSupplier = monoSupplier;
			return this.spec;
		}

	}
}
