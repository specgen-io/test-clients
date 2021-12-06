import * as puppeteer from './puppeteer'

import { suite, Context } from 'uvu';
import * as assert from 'uvu/assert';

import {Message, Choice} from '../src/test-service/models'
import * as echo from '../src/test-service/echo';

declare global {
  interface Window {
    echoClient: (config: {baseURL: string}) => echo.EchoClient    
  }
}

const config = {baseURL: process.env.SERVICE_URL!}

const mod = suite('mod');
mod.before(puppeteer.setup);
mod.before.each(puppeteer.homepage);
mod.after(puppeteer.reset);

mod('echoBody', async (context: Context) => {
  const { page } = context
  let response = await page.evaluate(async (config) => {
    const client = window.echoClient(config)
    let body: Message = {int_field: 123, string_field: "the string"}
    return await client.echoBody({body})
  }, config)
  let expected: Message = {int_field: 123, string_field: "the string"}
  assert.equal(response, expected, 'response matches request')
})

mod('echoQuery', async (context: Context) => {
  const { page } = context
  let response = await page.evaluate(async (config) => {
    const client = window.echoClient(config)
    return await client.echoQuery({intQuery: 123, stringQuery: "the string"})
  }, config)
  let expected: Message = {int_field: 123, string_field: "the string"}
  assert.equal(response, expected, 'response matches expected')
})

mod('echoHeader', async context => {
  const { page } = context
  let response = await page.evaluate(async (config) => {
    const client = window.echoClient(config)
    return await client.echoHeader({intHeader: 123, stringHeader: "the string"})
  }, config)
  let expected: Message = {int_field: 123, string_field: "the string"}
  assert.equal(response, expected, 'response matches expected')
})

mod('echoUrlParams', async context => {
  const { page } = context
  let response = await page.evaluate(async (config) => {
    const client = window.echoClient(config)
    return await client.echoUrlParams({intUrl: 123, stringUrl: "the string"})
  }, config)
  let expected: Message = {int_field: 123, string_field: "the string"}
  assert.equal(response, expected, 'response matches expected')
})

// test('checkQuery', async context => {
//   let client = checkClient(config)
//   let body: Message = {int_field: 123, string_field: "the string"}
//     await client.checkQuery({
//         pString: "the string",
//         pStringArray: ["string 1", "string 2"],
//         pDate: "2021-01-01",
//         pDateArray: ["2021-01-02"],
//         pDatetime: new Date("2021-01-02T23:54"),
//         pInt: 123,
//         pLong: 123,
//         pDecimal: 123,
//         pEnum: Choice.SECOND_CHOICE,
//         pStringOpt: "some string",
//         pStringDefaulted: "the string",
//     })
// })


mod.run();