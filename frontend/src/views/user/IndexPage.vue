<template>
    <div
        class="min-h-screen bg-gradient-to-br from-gray-50 to-white flex flex-col"
    >
        <header class="bg-white shadow-md sticky top-0 z-50">
            <div
                class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6 flex justify-between items-center flex-wrap gap-4"
            >
                <h1 class="text-3xl font-bold text-blue-700">
                    Finance Control
                </h1>
                <div class="flex gap-3">
                    <a
                        href="/login"
                        class="text-white bg-blue-600 hover:bg-blue-700 px-5 py-2 rounded-full transition"
                        >Login</a
                    >
                    <a
                        href="/registro"
                        class="text-blue-600 border border-blue-600 hover:bg-blue-50 px-5 py-2 rounded-full transition"
                        >Registrar</a
                    >
                </div>
            </div>
        </header>

        <section class="py-20 bg-white">
            <div
                class="max-w-7xl mx-auto px-4 grid grid-cols-1 md:grid-cols-2 gap-12 items-center"
            >
                <div>
                    <h2
                        class="text-4xl sm:text-5xl font-extrabold text-gray-900 leading-tight mb-6"
                    >
                        Organize sua vida financeira de forma simples
                    </h2>
                    <p class="text-base sm:text-lg text-gray-600 mb-6">
                        Com o Finance Control, você tem o controle dos seus
                        gastos, receitas e metas financeiras em um só lugar.
                        Visualize gráficos, receba alertas e planeje seu futuro
                        financeiro com facilidade.
                    </p>
                    <a
                        href="/registro"
                        class="bg-green-600 hover:bg-green-700 text-white px-8 py-3 rounded-full text-lg transition block w-fit"
                        >Criar conta gratuita</a
                    >
                </div>
                <div>
                    <img
                        src="https://fakeimg.pl/600x400/"
                        alt="Ilustração Financeira"
                        class="rounded-xl shadow-lg w-full h-auto"
                    />
                </div>
            </div>
        </section>

        <section class="bg-gray-50 py-20">
            <div class="max-w-6xl mx-auto px-4">
                <h3 class="text-3xl font-bold text-center text-gray-800 mb-12">
                    O que você pode fazer com o Finance Control
                </h3>
                <div
                    class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-10"
                >
                    <div class="bg-white shadow-md rounded-xl p-6 text-center">
                        <img
                            src="https://fakeimg.pl/100/"
                            alt="Controle de Gastos"
                            class="mx-auto mb-4"
                        />
                        <h4 class="text-xl font-semibold mb-2">
                            Controle de Gastos
                        </h4>
                        <p class="text-gray-600">
                            Registre e categorize seus gastos para entender para
                            onde seu dinheiro está indo.
                        </p>
                    </div>
                    <div class="bg-white shadow-md rounded-xl p-6 text-center">
                        <img
                            src="https://fakeimg.pl/100/"
                            alt="Metas Financeiras"
                            class="mx-auto mb-4"
                        />
                        <h4 class="text-xl font-semibold mb-2">
                            Metas Financeiras
                        </h4>
                        <p class="text-gray-600">
                            Defina objetivos como comprar um carro, fazer uma
                            viagem ou quitar dívidas.
                        </p>
                    </div>
                    <div class="bg-white shadow-md rounded-xl p-6 text-center">
                        <img
                            src="https://fakeimg.pl/100/"
                            alt="Gráficos e Relatórios"
                            class="mx-auto mb-4"
                        />
                        <h4 class="text-xl font-semibold mb-2">
                            Gráficos e Relatórios
                        </h4>
                        <p class="text-gray-600">
                            Visualize seus dados financeiros em gráficos claros
                            e relatórios detalhados.
                        </p>
                    </div>
                </div>
            </div>
        </section>

        <section class="bg-blue-700 text-white py-16 text-center px-4">
            <div class="max-w-4xl mx-auto">
                <h3 class="text-3xl font-bold mb-4">
                    Pronto para controlar suas finanças?
                </h3>
                <p class="text-lg mb-6">
                    Comece agora mesmo com uma conta gratuita e veja a diferença
                    no seu bolso.
                </p>
                <a
                    href="/registro"
                    class="bg-white text-blue-700 font-semibold px-8 py-3 rounded-full hover:bg-gray-100 transition inline-block"
                    >Quero começar</a
                >
            </div>
        </section>

        <footer class="bg-gray-900 text-white py-8">
            <div
                class="max-w-7xl mx-auto px-4 flex flex-col md:flex-row justify-between items-center gap-4 text-center"
            >
                <p>
                    &copy; 2025 Finance Control. Todos os direitos reservados.
                </p>
                <div class="flex gap-4 flex-wrap justify-center">
                    <a href="#" class="hover:text-blue-400"
                        >Política de Privacidade</a
                    >
                    <a href="#" class="hover:text-blue-400">Termos de Uso</a>
                    <a href="#" class="hover:text-blue-400">Contato</a>
                </div>
            </div>
        </footer>
    </div>
</template>
