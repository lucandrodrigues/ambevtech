export let endpointBackend = document.location.origin.split(':').length === 2 ?
    document.location.origin + '/api/' :
    document.location.origin.indexOf('localhost') || document.location.origin.indexOf('127.0.0.1') >= 0 ?
        document.location.origin.split(':')[0] + ':' + document.location.origin.split(':')[1] + ':8080/' :
        document.location.origin.split(':')[0] + ':' + document.location.origin.split(':')[1] + '/api/';


export let estados = ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG',
    'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'];
