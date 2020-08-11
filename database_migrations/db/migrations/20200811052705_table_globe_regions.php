<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableGlobeRegions extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $globeRegions = $this->table('globe_regions', ['signed' => false]);
        $globeRegions->addColumn('country_code', 'string', ['limit' => 2])
            ->addColumn('title', 'string', ['limit' => 120])
            ->create();
    }
}
