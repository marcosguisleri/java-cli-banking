# 🏦 Java CLI Banking System

### *Sistema bancário em linha de comando que vai do zero ao hero em POO*

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![CLI](https://img.shields.io/badge/Interface-CLI-4A90E2?style=for-the-badge&logo=windowsterminal&logoColor=white)
![POO](https://img.shields.io/badge/Paradigma-POO-9B59B6?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20Evolução-FFD43B?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-00C853?style=for-the-badge)

**Um projeto hands-on para dominar Programação Orientada a Objetos na prática**

[Características](#-características) • [Instalação](#️-instalação-e-execução) • [Como Usar](#-como-usar) • [Arquitetura](#️-arquitetura) • [Roadmap](#-roadmap)

---

## 🎯 Sobre o Projeto

Um **mini-sistema bancário** desenvolvido em Java puro com interface CLI para praticar conceitos fundamentais de POO: encapsulamento, validações, tratamento de exceções, separação de responsabilidades e gerenciamento de estado.

**Funcionalidades implementadas:**
- 🏦 Criação e gestão de contas bancárias
- 💰 Operações: depósito, saque e transferência
- 📊 Extrato completo com histórico temporal
- ✅ Validações robustas e tratamento de erros

Ideal para quem está:
- 📚 Aprendendo Java e POO
- 💪 Querendo praticar validações e regras de negócio
- 🏗️ Buscando entender separação de camadas
- 🧪 Se preparando para adicionar testes unitários

---

## ✨ Características

### 🏦 Gestão de Contas
- ✅ Criação com número único (001-999)
- ✅ Validação de titular obrigatório
- ✅ Prevenção de duplicidades
- ✅ Limite de 10 contas (array fixo)
- ✅ Listagem e seleção interativa

### 💰 Operações Bancárias
- ✅ **Depósito** com validação de valor
- ✅ **Saque** com verificação de saldo
- ✅ **Transferência** entre contas com validações completas
- ✅ **Extrato** completo com histórico e timestamps
- ✅ **Consulta** de saldo em tempo real
- ✅ Tratamento robusto de erros
- ✅ Mensagens de feedback claras

### 📊 Histórico e Rastreabilidade
- ✅ Extrato detalhado de todas operações
- ✅ Timestamp automático (`dd/MM/yyyy HH:mm:ss`)
- ✅ Registro de depósitos, saques e transferências
- ✅ Indicação de origem/destino em transferências
- ✅ Saldo após cada operação

---

## 🎮 Como Usar

### Fluxo Completo

```
┌─────────────────────────────────────┐
│   🏦 SISTEMA BANCÁRIO - MENU        │
├─────────────────────────────────────┤
│ 1. Adicionar conta                  │
│ 2. Selecionar conta                 │
│ 3. Sair                             │
└─────────────────────────────────────┘
```

**1️⃣ Adicionar Nova Conta**
```
Escolha: 1
Digite o nome do titular: Maria Silva
✓ Conta 427 criada com sucesso para Maria Silva!
```

**2️⃣ Selecionar e Operar**
```
Escolha: 2

Contas disponíveis:
  → 427 - Maria Silva (Saldo: R$ 0.00)

Digite o número da conta: 427

┌─────────────────────────────────────┐
│   CONTA: 427 - Maria Silva          │
├─────────────────────────────────────┤
│ 1. Depositar                        │
│ 2. Sacar                            │
│ 3. Consultar saldo                  │
│ 4. Ver extrato                      │
│ 5. Transferir                       │
│ 6. Voltar                           │
└─────────────────────────────────────┘
```

**3️⃣ Realizar Operações**
```
Escolha: 1
Valor para depósito: 1000
✓ Depósito realizado! Novo saldo: R$ 1000.00

Escolha: 2
Valor para saque: 250
✓ Saque realizado! Novo saldo: R$ 750.00

Escolha: 3
💰 Saldo atual: R$ 750.00

Escolha: 4
📊 Extrato:
16/02/2026 14:32:15 DEPOSITO +1000.0 | Saldo: 1000.0
16/02/2026 14:33:42 SAQUE -250.0 | Saldo: 750.0

Escolha: 5
Origem: 427 — Maria Silva
Destino (número): 123
Valor (R$): 200
✓ Transferência realizada! Novo saldo: R$ 550.00
```

---

## 🛡️ Regras e Validações

| Operação | Validação | Comportamento |
|----------|-----------|---------------|
| **Criar conta** | `titular` não pode ser vazio | Solicita novamente até válido |
| **Criar conta** | `numero` deve ser único | Gera novo número automaticamente |
| **Depositar** | `valor > 0` | Rejeita valores negativos/zero |
| **Sacar** | `valor > 0` | Lança `IllegalArgumentException` |
| **Sacar** | `valor <= saldo` | Retorna `false` se saldo insuficiente |
| **Transferir** | `valor > 0` | Lança `IllegalArgumentException` |
| **Transferir** | Contas existentes | Valida origem e destino |
| **Transferir** | Contas diferentes | Impede transferência para mesma conta |
| **Transferir** | `valor <= saldo` | Retorna `false` se saldo insuficiente |
| **Extrato** | Todas operações | Registra automaticamente com timestamp |
| **Entrada inválida** | Letras em campos numéricos | Captura `NumberFormatException` |

### 🔒 Proteções Implementadas

```java
// ❌ Não aceito
depositar(-50);                    // IllegalArgumentException
sacar(0);                          // IllegalArgumentException
sacar(1000);                       // Retorna false (saldo insuficiente)
transferir("001", "002", 0);       // IllegalArgumentException
transferir("001", "001", 100);     // Mesma conta (IllegalArgumentException)
transferir("001", "999", 100);     // Conta inexistente (IllegalArgumentException)
new Conta("", "");                 // IllegalArgumentException

// ✅ Aceito
depositar(100);                    // Registra no extrato
sacar(50);                         // Registra no extrato
transferir("001", "002", 50);      // Registra em ambas contas
getExtrato();                      // Retorna histórico completo
new Conta("123", "João");
```

---

## 🏗️ Arquitetura

```
src/
├── main/
│   └── java/
│       └── br/com/guisleri/
│           ├── cli/
│           │   └── Main.java           # 🎨 Interface CLI
│           └── models/
│               ├── Banco.java          # 🏦 Gerenciador de contas
│               └── ContaBancaria.java  # 💳 Regras de negócio
```

### 📐 Princípios Aplicados

| Camada | Responsabilidade | Decisões de Design |
|--------|------------------|-------------------|
| **CLI** (`Main`) | Interface do usuário | Menus, inputs, outputs, formatação |
| **Domínio** (`models`) | Regras de negócio | Validações, operações, estado |

> **💡 Separação limpa**: O domínio não conhece a CLI, facilitando evolução para API REST ou GUI desktop no futuro.

### 🔄 Fluxo de Dados

```
👤 Usuário 
    ↓
Main.java (CLI)
    ↓
Banco.java (Gerenciador)
    ↓
ContaBancaria.java (Regras de Negócio)
    ↓
Validações e Operações
    ↓
Resultado retorna ao Usuário
```

---

## ⚙️ Instalação e Execução

### Pré-requisitos

- ☕ Java 17 ou superior
- 📦 Maven 3.6+
- 💻 IDE (IntelliJ IDEA recomendado) ou terminal

### 🚀 Opção 1: IntelliJ IDEA

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/java-cli-banking.git

# 2. Abra o projeto no IntelliJ

# 3. Execute Main.java
# Navegue até: src/main/java/br/com/guisleri/cli/Main.java
# Clique com botão direito → Run 'Main.main()'
```

### 🖥️ Opção 2: Terminal (Maven)

```bash
# 1. Clone e entre no diretório
git clone https://github.com/seu-usuario/java-cli-banking.git
cd java-cli-banking

# 2. Compile o projeto
mvn clean compile

# 3. Execute
mvn exec:java -Dexec.mainClass="br.com.guisleri.cli.Main"

# Ou compile e rode manualmente
mvn package
java -cp target/classes br.com.guisleri.cli.Main
```

### ✅ Verificar Instalação

```bash
mvn test  # Valida compilação e configuração
```

---

## 🚀 Roadmap

### ✅ Concluído

- [x] **Transferência entre contas**
  - Transferir valor entre conta origem e destino
  - Validações: valor > 0, contas existentes, contas diferentes, saldo suficiente
  - Registro em ambas as contas

- [x] **Extrato de operações**
  - Histórico de depósitos/saques/transferências por conta
  - Timestamp automático com `LocalDateTime`
  - Formato: `dd/MM/yyyy HH:mm:ss`
  - Saldo após cada operação

### 🎯 Próximas Features

#### 💾 Fase 1: Persistência

- [ ] **Salvar dados em JSON (Gson)**
  - Auto-save ao sair do sistema
  - Auto-load ao iniciar
  - DTOs para separar domínio de I/O

- [ ] **Camada de infraestrutura**
  - Repository pattern
  - Interface `ContaRepository`
  - Implementação `JsonContaRepository`
  - Desacoplamento entre domínio e persistência

#### 🧪 Fase 2: Qualidade e Testes

- [ ] **Testes com JUnit 5**
  - Testes unitários do domínio (`ContaBancaria`, `Banco`)
  - Testes de validações e regras de negócio
  - Testes de casos extremos (edge cases)
  - Testes de transferências e extrato
  - Coverage > 80%

- [ ] **Melhoria de código**
  - Formatação de valores monetários
  - Constantes para mensagens
  - Enum para tipos de operação

#### 🌐 Fase 3: Evolução da Interface

- [ ] **API REST com Spring Boot**
  - Endpoints RESTful
  - Swagger/OpenAPI
  - DTOs de request/response
  - Autenticação básica

- [ ] **Interface Gráfica (JavaFX)**
  - Dashboard de contas
  - Gráficos de movimentação
  - Tema moderno
  - Exportação de extrato em PDF

---

## 📚 Conceitos Praticados

### 🎓 Fundamentos Java
- ☕ Sintaxe e estruturas de controle
- 📦 Pacotes e organização
- 🔧 Maven e gestão de dependências
- 📅 `LocalDateTime` e formatação de datas
- 📋 `ArrayList` e manipulação de listas

### 🏛️ POO Avançado
- 🎭 Encapsulamento
- 🧩 Separação de responsabilidades
- 🏗️ Design orientado a domínio
- 🔗 Comunicação entre objetos (Banco ↔ ContaBancaria)

### 🛡️ Validações
- ✅ Validação de entrada
- 🚫 Prevenção de estados inválidos
- ⚠️ Exceções customizadas
- 🔄 Validações complexas (transferências)

### 🐛 Tratamento de Erros
- 🎯 `IllegalArgumentException`
- 🔢 `NumberFormatException`
- 📋 Mensagens de feedback claras
- ↩️ Retornos booleanos para operações

### 📊 Persistência de Estado
- 💾 Histórico de operações
- ⏱️ Registro temporal de eventos
- 🔍 Auditoria de transações

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. 🍴 Fazer fork do projeto
2. 🌿 Criar uma branch (`git checkout -b feature/MinhaFeature`)
3. 💾 Commitar mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. 📤 Push para a branch (`git push origin feature/MinhaFeature`)
5. 🎉 Abrir um Pull Request

---

## 📝 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

```
MIT License - Livre para usar, modificar e distribuir
```

---

## 👨‍💻 Autor

Desenvolvido com ☕ e 💙 por **Marcos Guisleri**

---

### ⭐ Se este projeto te ajudou, considere dar uma estrela!

**Aprender fazendo é o melhor caminho** 🚀