package dk.dm844.bsg

import grails.transaction.Transactional

@Transactional
class QuoteService {

	static List quotes = [
			'''Commander William Adama: There's a reason you separate military and the police. One fights the enemies of the state, the other serves and protects the people. When the military becomes both, then the enemies of the state tend to become the people. ''',
			'''Doctor Gaius Baltar: All right, that's it! No more Mr. Nice Gaius!  ''',
			''' Commander William Adama: Sometimes, you have to roll a hard six. ''',
			'''Commander William Adama: Starbuck, what do you hear?<br/>
Lt. Kara 'Starbuck' Thrace: Nothing but the rain.<br/>
Commander William Adama: Then grab your gun and bring in the cat.<br/>
Lt. Kara 'Starbuck' Thrace: Boom, boom, boom!  ''',
			'''

Captain Lee 'Apollo' Adama: I thought we were sparring.<br/>
Commander William Adama: That's why you don't win.  ''',
			'''

Lt. Kara 'Starbuck' Thrace: I have my flaws, too.<br/>
Col. Saul Tigh: The difference is my flaws are personal. Yours are professional.  ''',
			'''

Commander William Adama: I gave the order, Son. It was my responsibility.<br/>
Captain Lee 'Apollo' Adama: I pulled the trigger. That's mine. ''',
			'''

[when Starbuck has landed in the Cylon ship]<br/>
Captain Lee 'Apollo' Adama: Boy, when you take a souvenir, you don't screw around.  ''',
			'''Lt. Karl 'Helo' Agathon: That's my Raptor wranglers, always looking for new and interesting ways to get killed.  ''',
			'''Doctor Gaius Baltar: You'll forgive me, Madam President, if I don't wish to be executed based solely on your... gut feeling.  ''',
	]


	String getRandomQuote() {
		quotes.get(Random.newInstance().nextInt(quotes.size()))
	}

	String addQuote(String quote) {
		quotes << quote
	}

}
