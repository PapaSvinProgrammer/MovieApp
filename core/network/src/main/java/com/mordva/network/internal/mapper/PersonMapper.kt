package com.mordva.network.internal.mapper

import com.mordva.model.person.Award
import com.mordva.model.person.Nomination
import com.mordva.model.person.NominationAward
import com.mordva.model.person.Person
import com.mordva.model.person.PersonMovie
import com.mordva.model.person.Place
import com.mordva.model.person.Profession
import com.mordva.model.person.Spouse
import com.mordva.network.internal.model.person.AwardDto
import com.mordva.network.internal.model.person.NominationAwardDto
import com.mordva.network.internal.model.person.NominationDto
import com.mordva.network.internal.model.person.PersonDto
import com.mordva.network.internal.model.person.PersonMovieDto
import com.mordva.network.internal.model.person.PlaceDto
import com.mordva.network.internal.model.person.ProfessionDto
import com.mordva.network.internal.model.person.SpouseDto

internal fun AwardDto.toDomain(): Award = Award(
    title = this.title,
    year = this.year
)

internal fun NominationAwardDto.toDomain(): NominationAward = NominationAward(
    nomination = this.nomination?.toDomain(),
    winning = this.winning,
    personId = this.personId,
    movie = this.movie?.toDomain()
)

internal fun NominationDto.toDomain(): Nomination = Nomination(
    award = this.award?.toDomain(),
    title = this.title
)

internal fun PersonDto.toDomain(): Person = Person(
    id = id,
    name = name,
    enName = enName,
    photo = photo,
    sex = sex,
    growth = growth,
    birthday = birthday,
    death = death,
    age = age,
    countAwards = countAwards,
    spouses = spouse.map { it.toDomain() },
    birthPlace = birthPlace.map { it.toDomain() },
    deathPlace = deathPlace.map { it.toDomain() },
    professions = profession.map { it.toDomain() },
    facts = facts.map { it.toDomain() },
    movies = movies.map { it.toDomain() }
)

internal fun PersonMovieDto.toDomain(): PersonMovie = PersonMovie(
    id = id,
    name = name,
    enName = enName,
    photo = photo,
    description = description,
    profession = profession,
    enProfession = enProfession
)

internal fun PlaceDto.toDomain(): Place = Place(
    value = value
)

internal fun ProfessionDto.toDomain(): Profession = Profession(
    value = value
)

internal fun SpouseDto.toDomain(): Spouse = Spouse(
    id = id,
    name = name,
    children = children,
    divorced = divorced,
    relation = relation
)
