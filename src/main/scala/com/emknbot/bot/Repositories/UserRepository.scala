package com.emknbot.bot

trait UserRepository[F[_], Id] extends Repository[F, Id, User]