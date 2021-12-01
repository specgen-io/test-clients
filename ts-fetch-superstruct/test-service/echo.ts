import { URL, URLSearchParams } from 'url'
import * as t from './superstruct'
import * as models from './models'
import fetch from 'node-fetch'


export const client = (config: {baseURL: string}) => {
    return {
        echoBody: async (parameters: {body: models.Message}): Promise<models.Message> => {
            const bodyJson = t.encode(models.TMessage, parameters.body)
            const url = config.baseURL+`/echo/body`
            const response = await fetch(url, {method: 'POST', body: JSON.stringify(bodyJson)})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoQuery: async (parameters: {intQuery: number, stringQuery: string}): Promise<models.Message> => {
            const params = {
                "int_query": String(parameters.intQuery),
                "string_query": String(parameters.stringQuery),
            }
            const url = config.baseURL+`/echo/query?${new URLSearchParams(params)}`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoHeader: async (parameters: {intHeader: number, stringHeader: string}): Promise<models.Message> => {
            const headers = {
                "Int-Header": String(parameters.intHeader),
                "String-Header": String(parameters.stringHeader),
            }
            const url = config.baseURL+`/echo/header`
            const response = await fetch(url, {method: 'GET', headers: headers})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        echoUrlParams: async (parameters: {intUrl: number, stringUrl: string}): Promise<models.Message> => {
            const url = config.baseURL+`/echo/url_params/${parameters.intUrl}/${parameters.stringUrl}`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve(t.decode(models.TMessage, response.json()))
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },

        sameOperationName: async (): Promise<SameOperationNameResponse> => {
            const url = config.baseURL+`/echo/same_operation_name`
            const response = await fetch(url, {method: 'GET'})
            switch (response.status) {
                case 200:
                    return Promise.resolve({ status: "ok" })
                case 403:
                    return Promise.resolve({ status: "forbidden" })
                default:
                    throw new Error(`Unexpected status code ${ response.status }`)
            }
        },
    }
}

export type SameOperationNameResponse =
    | { status: "ok" }
    | { status: "forbidden" }
