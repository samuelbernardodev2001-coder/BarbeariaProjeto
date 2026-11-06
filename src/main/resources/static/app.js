// Simulação de backend simples para demo
const usuarios = [];
const funcionarios = [
  { id: 1, nome: 'Carlos', especialidade: 'Corte Clássico' },
  { id: 2, nome: 'Ana', especialidade: 'Barba' },
  { id: 3, nome: 'João', especialidade: 'Cortes Modernos' }
];

const cortes = [
  { id: 1, nome: 'Corte Clássico', preco: 30, funcionarioId: 1 },
  { id: 2, nome: 'Barba', preco: 20, funcionarioId: 2 },
  { id: 3, nome: 'Corte Moderno', preco: 40, funcionarioId: 3 }
];

const avaliacoes = [];

let usuarioLogado = null;

// Controle de visibilidade
const loginSection = document.getElementById('login-section');
const registerSection = document.getElementById('register-section');
const dashboardSection = document.getElementById('dashboard-section');

const linkRegister = document.getElementById('link-register');
const linkLogin = document.getElementById('link-login');

linkRegister.addEventListener('click', e => {
  e.preventDefault();
  loginSection.classList.add('hidden');
  registerSection.classList.remove('hidden');
});

linkLogin.addEventListener('click', e => {
  e.preventDefault();
  registerSection.classList.add('hidden');
  loginSection.classList.remove('hidden');
});

// Login
document.getElementById('login-form').addEventListener('submit', e => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const username = formData.get('username');
  const senha = formData.get('senha');
  const usuario = usuarios.find(u => u.username === username && u.senha === senha);
  if(usuario){
    usuarioLogado = usuario;
    mostrarDashboard();
  } else {
    alert('Usuário ou senha inválidos.');
  }
});

// Cadastro
document.getElementById('register-form').addEventListener('submit', e => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const novoUsuario = {
    nome: formData.get('nome'),
    email: formData.get('email'),
    username: formData.get('username'),
    senha: formData.get('senha')
  };
  if(usuarios.find(u => u.username === novoUsuario.username)){
    alert('Usuário já existe!');
    return;
  }
  usuarios.push(novoUsuario);
  alert('Cadastro realizado com sucesso! Faça login.');
  registerSection.classList.add('hidden');
  loginSection.classList.remove('hidden');
});

// Logout
document.getElementById('logout-btn').addEventListener('click', () => {
  usuarioLogado = null;
  dashboardSection.classList.add('hidden');
  loginSection.classList.remove('hidden');
});

// Mostrar Dashboard
function mostrarDashboard(){
  loginSection.classList.add('hidden');
  registerSection.classList.add('hidden');
  dashboardSection.classList.remove('hidden');
  carregarFuncionarios();
  carregarCortes();
  carregarAvaliacoes();
}

// Carregar funcionários
function carregarFuncionarios(){
  const container = document.getElementById('funcionarios-list');
  container.innerHTML = '';
  funcionarios.forEach(f => {
    const div = document.createElement('div');
    div.textContent = `${f.nome} - Especialidade: ${f.especialidade}`;
    container.appendChild(div);
  });
  const selectFunc = document.getElementById('funcionario-select');
  selectFunc.innerHTML = '';
  funcionarios.forEach(f => {
    const opt = document.createElement('option');
    opt.value = f.id;
    opt.textContent = f.nome;
    selectFunc.appendChild(opt);
  });
}

// Carregar cortes
function carregarCortes(){
  const container = document.getElementById('cortes-list');
  container.innerHTML = '';
  cortes.forEach(c => {
    const div = document.createElement('div');
    div.textContent = `${c.nome} - R$ ${c.preco},00`;
    container.appendChild(div);
  });
  const selectCorte = document.getElementById('corte-select');
  selectCorte.innerHTML = '';
  cortes.forEach(c => {
    const opt = document.createElement('option');
    opt.value = c.id;
    opt.textContent = c.nome;
    selectCorte.appendChild(opt);
  });
}

// Agendar corte
document.getElementById('agendamento-form').addEventListener('submit', e => {
  e.preventDefault();
  if(!usuarioLogado){
    alert('Faça login para agendar.');
    return;
  }
  const formData = new FormData(e.target);
  const funcionarioId = parseInt(formData.get('funcionario'));
  const corteId = parseInt(formData.get('corte'));
  const datahora = formData.get('datahora');
  alert(`Agendamento feito para ${datahora} com o barbeiro ID ${funcionarioId} para o corte ID ${corteId}`);
});

// Avaliar barbeiros
document.getElementById('avaliacao-form').addEventListener('submit', e => {
  e.preventDefault();
  if(!usuarioLogado){
    alert('Faça login para avaliar.');
    return;
  }
  const formData = new FormData(e.target);
  const nota = parseInt(formData.get('nota'));
  const comentario = formData.get('comentario');
  avaliacoes.push({ usuario: usuarioLogado.username, nota, comentario });
  alert('Avaliação enviada!');
  carregarAvaliacoes();
  e.target.reset();
});

// Carregar avaliações
function carregarAvaliacoes(){
  const container = document.getElementById('avaliacoes-list');
  container.innerHTML = '';
  avaliacoes.forEach(a => {
    const div = document.createElement('div');
    div.innerHTML = `<strong>${a.usuario}</strong> - Nota: ${a.nota}<br/>${a.comentario}`;
    container.appendChild(div);
  });
}

// Mostrar status da loja
function mostrarStatusLoja(){
  const statusEl = document.getElementById('status');
  const agora = new Date();
  const abre = new Date();
  abre.setHours(9,0,0);
  const fecha = new Date();
  fecha.setHours(19,0,0);
  if(agora >= abre && agora <= fecha){
    statusEl.textContent = 'Aberta';
    statusEl.classList.remove('closed');
  } else {
    statusEl.textContent = 'Fechada';
    statusEl.classList.add('closed');
  }
}

// Atualizar status da loja a cada minuto
mostrarStatusLoja();
setInterval(mostrarStatusLoja, 60000);
