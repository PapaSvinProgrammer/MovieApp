package com.example.network.internal.mapper

import com.example.model.person.Award
import com.example.model.person.Nomination
import com.example.model.person.NominationAward
import com.example.model.person.Person
import com.example.model.person.PersonMovie
import com.example.model.person.Place
import com.example.model.person.Profession
import com.example.model.person.Spouse
import com.example.network.internal.model.person.AwardDto
import com.example.network.internal.model.person.NominationAwardDto
import com.example.network.internal.model.person.NominationDto
import com.example.network.internal.model.person.PersonDto
import com.example.network.internal.model.person.PersonMovieDto
import com.example.network.internal.model.person.PlaceDto
import com.example.network.internal.model.person.ProfessionDto
import com.example.network.internal.model.person.SpouseDto

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
