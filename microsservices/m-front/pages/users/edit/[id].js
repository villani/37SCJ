import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Link from 'next/link';

function Edit({ usuario }) {

    return (
        <Container>
        <Row>
            <Col>
              <h1>Edição de Usuário</h1>
              <p><Link href="/users">Cancelar</Link></p>
              <Form>
                <Form.Group controlId="name">
                  <Form.Label>Nome:</Form.Label>
                  <Form.Control type="text" value={usuario.name} placeholder="Informe o nome do usuário." />
                </Form.Group>

                <Form.Group controlId="email">
                  <Form.Label>E-mail</Form.Label>
                  <Form.Control type="email" value={usuario.email} placeholder="Informe o e-mail." />
                </Form.Group>

                <Form.Group controlId="password">
                  <Form.Label>Senha:</Form.Label>
                  <Form.Control type="password" value={usuario.password} placeholder="Senha" />
                </Form.Group>

                <Form.Group controlId="maritalStatus">
                  <Form.Label>Estado civil:</Form.Label>
                  <Form.Control as="select" defaultValue={usuario.maritalStatus}>
                    <option>Solteiro(a)</option>
                    <option>Casado(a)</option>
                    <option>Divorciado(a)</option>
                    <option>União estável</option>
                  </Form.Control>
                </Form.Group>

                <Form.Group controlId="birthDate">
                  <Form.Label>Data de nascimento:</Form.Label>
                  <Form.Control type="text" value={usuario.birthDate} />
                </Form.Group>

                <Form.Group controlId="allowNotifications">
                  <Form.Check
                    value={usuario.allowNotifications}
                    required
                    label="Permitir notificações"
                    feedback="Você precisa permitir se desejar ser notificado."
                  />
                </Form.Group>
               
                <Button variant="primary" type="submit">
                  Gravar
                </Button>
              </Form>
            </Col>
        </Row>
    </Container>
    )
}

export async function getStaticPaths() {
    const res = await fetch('http://localhost:8081/users')
    const usuarios = await res.json()
  
    // Get the paths we want to pre-render based on usuarios
    console.log(usuarios)
    console.log(usuarios[0].id)
    const paths = usuarios.map((usuario) => ({
      params: { id: usuario.id.toString() },
    }))
    console.log(paths)
  
    // We'll pre-render only these paths at build time.
    // { fallback: false } means other routes should 404.
    return { paths, fallback: false }
  }

export async function getStaticProps({ params }) {

    console.log(params)

    const endpoint = `http://localhost:8081/users/${params.id}`
    console.log(endpoint)
    const res = await fetch(endpoint)
    const usuario = await res.json()
  
    return {
        props: {
            usuario,
        },
    }
  }

export default Edit