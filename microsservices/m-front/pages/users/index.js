import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';
import Alert from 'react-bootstrap/Alert';
import Link from 'next/link';

function Home({usuarios}) {

  if (usuarios && usuarios.length > 0)
    return (
      <Container>
          <Row>
              <Col>
                <h1>Lista de Usuário</h1> 
                <p><Link href="/users/edit">Adicionar usuário</Link></p> 
                <Table striped hover>
                  <thead>
                      <tr>
                      <th>#</th>
                      <th>Nome</th>
                      <th>E-mail</th>
                      <th colspan="2">Ações</th>
                      </tr>
                  </thead>
                  
                  <tbody>
                    {usuarios.map((usuario) => (
                      <tr>
                        <td>{usuario.id}</td>
                        <td>{usuario.name}</td>
                        <td>{usuario.email}</td>
                        <td><Link href={'/users/edit/' + usuario.id}>Editar</Link></td>
                        <td>Excluir</td>
                      </tr>
                    ))}    
                  </tbody>
                  </Table>
              </Col>
          </Row>
      </Container>
    )
  else return (
    <Container>
      <Row>
          <Col>
            <h1>Lista de Usuário</h1> 
            <p><Link href="/users/edit">Adicionar usuário</Link></p>
            <Alert variant="info">Não há usuários!</Alert>
          </Col>
        </Row>
    </Container>
  )                      
}

export async function getStaticProps() {

    const res = await fetch("http://localhost:8081/users")
    const usuarios = await res.json()

    return {
        props: {
            usuarios,
        },
    }
}

export default Home