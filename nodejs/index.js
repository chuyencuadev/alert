const express = require('express');
const player = require('play-sound')(opts = {});
const vol = require('vol');
const app = express();

app.get('/commands/:name', function (req, res) {
  let filePath = './commands/' + req.params.name + '.mp3';

  vol.set(0.30, function() {
    player.play(filePath, function(err) {
      if (err) throw err;

      res.json({ data: { command: req.params.name } });
    })
  });
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!')
});
